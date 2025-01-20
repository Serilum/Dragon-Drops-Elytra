package com.natamus.dragondropselytra.events;

import com.natamus.collective.functions.MessageFunctions;
import com.natamus.dragondropselytra.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.regex.Pattern;

public class DragonEvent {
	public static void mobItemDrop(Level world, Entity entity, DamageSource damageSource) {
		if (world.isClientSide) {
			return;
		}
		
		if (!(entity instanceof EnderDragon)) {
			return;
		}
		
		Player player = null;
		BlockPos epos = entity.blockPosition();
		Entity source = damageSource.getEntity();
		
		if (source == null) {
			List<Entity> entitiesaround = world.getEntities(entity, new AABB(epos.getX()-30, epos.getY()-30, epos.getZ()-30, epos.getX()+30, epos.getY()+30, epos.getZ()+30));
			for (Entity ea : entitiesaround) {
				if (ea instanceof Player) {
					player = (Player)ea;
					break;
				}
			}
		}
		else if (source instanceof Player) {
			player = (Player)source;
		}
		
		MessageFunctions.broadcastMessage(world, "It seems like the slain Ender Dragon dropped an elytra! Perhaps it previously belonged to another adventurer?", ChatFormatting.DARK_GREEN);
		
		ItemStack elytrastack = new ItemStack(Items.ELYTRA, 1);

		String noSpaceEnchantList = ConfigHandler.elytraEnchantmentList.replaceAll(" ", "");
		final Pattern regexPattern = Pattern.compile("^[\\w]+:[\\d]+(,[\\w]+:[\\d]+)*$", Pattern.CASE_INSENSITIVE);
		boolean configCorrectFormat = regexPattern.matcher(noSpaceEnchantList).matches();

		if(!noSpaceEnchantList.isEmpty() & configCorrectFormat) {
			HolderLookup.RegistryLookup<Enchantment> lookup = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
			String[] configEnchants = noSpaceEnchantList.split(",");
			for (String enchantBundle : configEnchants) {
				String enchant = enchantBundle.split(":")[0];
				int power = Integer.parseInt(enchantBundle.split(":")[1]);
				elytrastack.enchant(lookup.getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace(enchant))), power);
			}
		}

		if (player == null) {
			ItemEntity elytra = new ItemEntity(world, epos.getX(), epos.getY()+1, epos.getZ(), elytrastack);
			world.addFreshEntity(elytra);
		}
		else {
			BlockPos pos = player.blockPosition();
			ItemEntity elytra = new ItemEntity(world, pos.getX(), pos.getY()+1, pos.getZ(), elytrastack);
			world.addFreshEntity(elytra);
			
			MessageFunctions.sendMessage(player, "The elytra dropped at your position!", ChatFormatting.YELLOW);
		}
	}
}
