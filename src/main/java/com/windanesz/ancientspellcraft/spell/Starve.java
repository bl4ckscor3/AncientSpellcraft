package com.windanesz.ancientspellcraft.spell;

import com.windanesz.ancientspellcraft.AncientSpellcraft;
import electroblob.wizardry.item.SpellActions;
import electroblob.wizardry.spell.SpellRay;
import electroblob.wizardry.util.SpellModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class Starve extends SpellRayAS {

	public Starve() {
		super("starve", SpellActions.POINT, false);
	}

	@Override
	protected boolean onEntityHit(World world, Entity target, Vec3d hit,
			@Nullable EntityLivingBase caster, Vec3d origin, int ticksInUse, SpellModifiers modifiers) {

		if (target instanceof EntityPlayer) {

			EntityPlayer targetPlayer = (EntityPlayer) target;

			int foodlevel = targetPlayer.getFoodStats().getFoodLevel();
			targetPlayer.getFoodStats().setFoodLevel(foodlevel / 2);

			float saturation = targetPlayer.getFoodStats().getSaturationLevel();
			targetPlayer.getFoodStats().setFoodSaturationLevel(saturation / 2);

			return true;
		}
		return false;
	}

	@Override
	protected boolean onBlockHit(World world, BlockPos pos, EnumFacing side, Vec3d hit,
			@Nullable EntityLivingBase caster, Vec3d origin, int ticksInUse, SpellModifiers modifiers) {
		return false;
	}

	@Override
	protected boolean onMiss(World world, @Nullable EntityLivingBase caster, Vec3d origin, Vec3d direction, int ticksInUse, SpellModifiers modifiers) {
		return false;
	}

	@Override
	public boolean canBeCastBy(EntityLiving npc, boolean override) { return true; }

	@Override
	public boolean canBeCastBy(TileEntityDispenser dispenser) { return true; }

	@Override
	protected void spawnParticle(World world, double x, double y, double z, double vx, double vy, double vz){
		world.spawnParticle(EnumParticleTypes.SPELL_WITCH, x, y, z, 0, 0, 0);
	}
}
