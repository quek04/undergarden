package quek.undergarden.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;
import quek.undergarden.client.model.animation.BattleaxeAnimation;

@SuppressWarnings("unused")
public class UGClientEnumExtensions {

	public static final EnumProxy<EquipmentClientInfo.LayerType> DWELLER_SADDLE = new EnumProxy<>(EquipmentClientInfo.LayerType.class, "undergarden:dweller_saddle");

	public static final EnumProxy<HumanoidModel.ArmPose> BATTLEAXE_ARM_POSE = new EnumProxy<>(HumanoidModel.ArmPose.class, true, true, (IArmPoseTransformer) BattleaxeAnimation::thirdPersonAttackHand);
}
