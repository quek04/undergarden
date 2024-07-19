package quek.undergarden.block;

import net.minecraft.util.StringRepresentable;

public enum InfuserState implements StringRepresentable {
	INACTIVE("inactive"),
	INFUSING_UTHERIUM("infusing_utherium"),
	INFUSING_ROGDORIUM("infusing_rogdorium")
	;

	private final String name;

	InfuserState(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
}
