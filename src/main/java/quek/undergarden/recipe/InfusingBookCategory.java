package quek.undergarden.recipe;

import net.minecraft.util.StringRepresentable;

public enum InfusingBookCategory implements StringRepresentable {
	PURIFYING("purifying"),
	CORRUPTING("corrupting"),
	MISC("misc");

	public static final StringRepresentable.EnumCodec<InfusingBookCategory> CODEC = StringRepresentable.fromEnum(InfusingBookCategory::values);
	private final String name;

	InfusingBookCategory(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
