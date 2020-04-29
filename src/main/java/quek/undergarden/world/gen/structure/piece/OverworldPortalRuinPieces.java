package quek.undergarden.world.gen.structure.piece;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import quek.undergarden.UndergardenMod;
import quek.undergarden.registry.UndergardenFeatures;

import java.util.List;
import java.util.Random;

public class OverworldPortalRuinPieces {

    private static final ResourceLocation RUIN = new ResourceLocation(UndergardenMod.MODID + ":overworld_portal_ruin");

    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
        int x = pos.getX();
        int z = pos.getZ();

        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.add(x, pos.getY(), z);
        pieceList.add(new OverworldPortalRuinPieces.Piece(templateManager, RUIN, blockpos, rotation));
    }

    public static class Piece extends TemplateStructurePiece {
        private ResourceLocation resourceLocation;
        private Rotation rotation;


        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(UndergardenFeatures.OVERWORLD_PORTAL_RUIN_TYPE, 0);
            resourceLocation = resourceLocationIn;
            BlockPos blockpos = new BlockPos(0,0,0);
            templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            rotation = rotationIn;
            setupPiece(templateManagerIn);
        }


        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(UndergardenFeatures.OVERWORLD_PORTAL_RUIN_TYPE, tagCompound);
            resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }


        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setMirror(Mirror.NONE);
            setup(template, this.templatePosition, placementsettings);
        }


        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }


        /*
         * If you added any data marker structure blocks to your structure, you can access and modify them here. In this case,
         * our structure has a data maker with the string "chest" put into it. So we check to see if the incoming function is
         * "chest" and if it is, we now have that exact position.
         *
         * So what is done here is we replace the structure block with a chest and we can then set the loottable for it.
         *
         * You can set other data markers to do other behaviors such as spawn a random mob in a certain spot, randomize what
         * rare block spawns under the floor, or what item an Item Frame will have.
         */
        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {

        }


        @Override
        public boolean func_225577_a_(IWorld worldIn, ChunkGenerator<?> p_225577_2_, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setMirror(Mirror.NONE);
            BlockPos blockpos = new BlockPos(0,0,0);;
            templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(0 - blockpos.getX(), 0, 0 - blockpos.getZ())));

            return super.func_225577_a_(worldIn, p_225577_2_, randomIn, structureBoundingBoxIn, chunkPos);
        }
    }
}
