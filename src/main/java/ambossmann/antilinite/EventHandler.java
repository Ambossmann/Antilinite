package ambossmann.antilinite;

import java.util.function.Predicate;

import ambossmann.antilinite.materials.MaterialParts;
import ambossmann.antilinite.materials.ModMaterial;
import ambossmann.antilinite.setup.Registration;
import ambossmann.antilinite.util.EntityHelper;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {

	private EventHandler() {
	}

	private static LivingEntity noKnockbackEntity;

	@SubscribeEvent
	public static void onEntityTarget(LivingSetAttackTargetEvent event) {
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		doubleHitDamage(event, EntityHelper::isPiglinMob, Registration.ANTILINITE);
		doubleHitDamage(event, EntityHelper::isEnderMob, Registration.ANTERIUM);
	}

	private static void doubleHitDamage(LivingHurtEvent event, Predicate<LivingEntity> entityChecker, ModMaterial material) {
		DamageSource source = event.getSource();
		if (entityChecker.test(event.getEntityLiving()) && source instanceof EntityDamageSource
				&& !(source instanceof IndirectEntityDamageSource)
				&& source.getEntity()instanceof LivingEntity attacker
				&& attacker.getItemInHand(InteractionHand.MAIN_HAND).getItem()instanceof TieredItem weapon
				&& weapon.getTier() == material.getToolTier()) {

			event.setAmount(event.getAmount() * 2);
			if (event.getEntity().level instanceof ServerLevel level) {
				LivingEntity entity = event.getEntityLiving();
				level.sendParticles(
						(SimpleParticleType) material.getPart(MaterialParts.ATTACK_PARTICLE).get(),
						entity.getX(), entity.getY() + entity.getBbHeight()*0.9, entity.getZ(), (int) event.getAmount(), 0.2,
						0.2, 0.2, 0.2);
			}
		}
	}

	@SubscribeEvent
	public static void onTick(PlayerTickEvent event) {
		damageNearbyEntities(event, EntityHelper::isPiglinMob, Registration.ANTILINITE);
		damageNearbyEntities(event, EntityHelper::isEnderMob, Registration.ANTERIUM);
	}

	private static void damageNearbyEntities(PlayerTickEvent event, Predicate<LivingEntity> entityChecker,
			ModMaterial material) {
		Player player = event.player;
		double reach = Math.floor(EntityHelper.entityArmorPieces(player, material.getArmorMat())) * 4.0;
		if (reach >= 1.0F) {
			player.level
					.getNearbyEntities(LivingEntity.class,
							TargetingConditions.forCombat().range(reach).selector(entityChecker), player,
							player.getBoundingBox().inflate(reach))
					.stream()
					.filter(entityChecker)
					.forEach(entity -> {
						noKnockbackEntity = entity;
						float damage = (float) ((Math.sqrt(reach)/Math.sqrt(entity.distanceTo(player))) * 1F);
						entity.hurt(new EntityDamageSource("magic", player).setMagic(), damage);
						if (entity.level instanceof ServerLevel level) {
							level.sendParticles(
									(SimpleParticleType) material.getPart(MaterialParts.ATTACK_PARTICLE)
											.get(),
									entity.getX(), entity.getY() + entity.getBbHeight() * 0.9, entity.getZ(), 1, 0.2,
									0.2, 0.2, 0.2);
						}
					});
		}
	}

	@SubscribeEvent
	public static void onKnockback(LivingKnockBackEvent event) {
		if (noKnockbackEntity == event.getEntityLiving()) {
			event.setCanceled(true);
		}
	}

}
