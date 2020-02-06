package quek.undergarden.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quek.undergarden.entity.*;
import quek.undergarden.entity.render.*;

import static quek.undergarden.Undergarden.MODID;

@SuppressWarnings("unused")
public class UndergardenEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MODID);

    public static final EntityType<RotwalkerEntity> rotwalker = EntityType.Builder.create(RotwalkerEntity::new, EntityClassification.MONSTER)
            .size(0.6f,2.5f).build("rotwalker");

    public static final RegistryObject<EntityType<RotwalkerEntity>> ROTWALKER = ENTITIES.register("rotwalker", () -> rotwalker);

    @OnlyIn(Dist.CLIENT)
    public void entityRender(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(RotwalkerEntity.class, RotwalkerRender::new);

    }
}
