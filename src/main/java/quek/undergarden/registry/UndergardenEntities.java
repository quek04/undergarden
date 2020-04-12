package quek.undergarden.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.*;

@SuppressWarnings("unused")
public class UndergardenEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, UndergardenMod.MODID);

    public static final EntityType<RotwalkerEntity> rotwalker = EntityType.Builder.create(RotwalkerEntity::new, EntityClassification.MONSTER)
            .size(0.6f,2.5f).build("rotwalker");
    public static final EntityType<RotbeastEntity> rotbeast = EntityType.Builder.create(RotbeastEntity::new, EntityClassification.MONSTER)
            .size(1,3).build("rotbeast");
    public static final EntityType<DwellerEntity> dweller = EntityType.Builder.create(DwellerEntity::new, EntityClassification.CREATURE)
            .size(1,2).build("dweller");
    public static final EntityType<GwiblingEntity> gwibling = EntityType.Builder.create(GwiblingEntity::new, EntityClassification.WATER_CREATURE)
            .size(.5F, .5F).build("gwibling");

    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> rotwalker);
    public static final RegistryObject<EntityType<RotbeastEntity>> ROTBEAST = ENTITIES.register("rotbeast", () -> rotbeast);
    public static final RegistryObject<EntityType<DwellerEntity>> DWELLER = ENTITIES.register("dweller", () -> dweller);
    public static final RegistryObject<EntityType<GwiblingEntity>> GWIBLING = ENTITIES.register("gwibling", () -> gwibling);

    /*
    @OnlyIn(Dist.CLIENT)
    public void entityRender(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RotwalkerEntity.class, RotwalkerRender::new);

    }
    */
}
