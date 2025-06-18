package quek.undergarden.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import quek.undergarden.event.UthericInfectionEvents;
import quek.undergarden.registry.UGAttachments;
import quek.undergarden.registry.UGTags;

import java.util.Collection;

public class InfectionCommand {

	private static final SimpleCommandExceptionType CANNOT_INFECT = new SimpleCommandExceptionType(Component.translatable("commands.undergarden.infection.cannot_infect"));
	private static final SimpleCommandExceptionType CANNOT_INFECT_MULTIPLE = new SimpleCommandExceptionType(Component.translatable("commands.undergarden.infection.cannot_infect_multiple"));

	public static LiteralArgumentBuilder<CommandSourceStack> register() {
		return Commands.literal("infection")
			.requires(cs -> cs.hasPermission(Commands.LEVEL_ADMINS))
			.then(Commands.argument("targets", EntityArgument.entities())
				.then(Commands.literal("set")
					.then(Commands.argument("amount", DoubleArgumentType.doubleArg(0.0D, 20.0D))
						.executes(context -> applyInfection(context.getSource(), EntityArgument.getEntities(context, "targets"), DoubleArgumentType.getDouble(context, "amount"))))));
	}

	private static int applyInfection(CommandSourceStack source, Collection<? extends Entity> targets, double amount) throws CommandSyntaxException {
		int skipped = 0;
		for (Entity entity : targets) {
			if (entity.getType().is(UGTags.Entities.IMMUNE_TO_INFECTION)) {
				skipped++;
				continue;
			}
			entity.setData(UGAttachments.UTHERIC_INFECTION, amount);
			UthericInfectionEvents.sendInfectionSyncPacket(entity);
		}

		if (targets.size() == 1) {
			if (skipped > 0) {
				throw CANNOT_INFECT.create();
			}
			source.sendSuccess(() -> Component.translatable("commands.undergarden.infection.success.single", amount, targets.iterator().next().getDisplayName()), true);
		} else {
			if (skipped >= targets.size()) {
				throw CANNOT_INFECT_MULTIPLE.create();
			}
			int finalSkipped = skipped;
			source.sendSuccess(() -> Component.translatable("commands.undergarden.infection.success.multiple", amount, targets.size() - finalSkipped), true);
			if (finalSkipped > 0) {
				source.sendSuccess(() -> Component.translatable("commands.undergarden.infection.skipped", finalSkipped), true);
			}
		}

		return Command.SINGLE_SUCCESS;
	}
}
