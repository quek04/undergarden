package quek.undergarden.registry;

import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quek.undergarden.UndergardenMod;
import quek.undergarden.entity.rotspawn.RotspawnSensor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = UndergardenMod.MODID)
public class UndergardenSensorTypes {

    public static final SensorType<RotspawnSensor> rotspawn_sensor = register("rotspawn_sensor", RotspawnSensor::new);

    @SubscribeEvent
    public static void registerSensors(RegistryEvent.Register<SensorType<?>> event) {
        event.getRegistry().register(rotspawn_sensor);
    }

    private static <U extends Sensor<?>> SensorType<U> register(String key, Supplier<U> p_220996_1_) {
        return Registry.register(Registry.SENSOR_TYPE, new ResourceLocation(UndergardenMod.MODID, key), new SensorType<>(p_220996_1_));
    }
}
