package ambossmann.antilinite;

import ambossmann.antilinite.materials.ModArmorMaterials;
import ambossmann.antilinite.materials.ModTiers;
import ambossmann.antilinite.setup.Registration;
import ambossmann.antilinite.util.EntityHelper;
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
		if (EntityHelper.isPiglinMob(event.getEntityLiving())) {
			DamageSource source = event.getSource();
			if (source instanceof EntityDamageSource && !(source instanceof IndirectEntityDamageSource)) {
				if (source.getEntity()instanceof LivingEntity attacker) {
					if (attacker.getItemInHand(InteractionHand.MAIN_HAND).getItem()instanceof TieredItem weapon) {
						if (weapon.getTier() == ModTiers.ANTILINITE) {
							event.setAmount(event.getAmount() * 2);
							if (event.getEntity().level instanceof ServerLevel level) {
								LivingEntity entity = event.getEntityLiving();
								level.sendParticles(Registration.ANTILINITE_ATTACK.get(), entity.getX(), entity.getY() + 1.8, entity.getZ(), (int) event.getAmount(), 0.2, 0.2, 0.2, 0.2);
							}
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onTick(PlayerTickEvent event) {
		Player player = event.player;
		double reach = Math.floor(EntityHelper.entityArmorPieces(player, ModArmorMaterials.ANTILINITE)) * 4.0;
		if (reach >= 1.0F) {
			player.level
					.getNearbyEntities(LivingEntity.class,
							TargetingConditions.forCombat().range(reach).selector(EntityHelper::isPiglinMob), player,
							player.getBoundingBox().inflate(reach))
					.stream()
					.filter(EntityHelper::isPiglinMob)
					.forEach(entity -> {
						noKnockbackEntity = entity;
						float damage = (float) ((4.0 - Math.sqrt(Math.sqrt(entity.distanceToSqr(player)))) * 0.125F * reach);
						entity.hurt(new EntityDamageSource("magic", player).setMagic(), damage);
						if (entity.level instanceof ServerLevel level) {
							level.sendParticles(Registration.ANTILINITE_ATTACK.get(), entity.getX(), entity.getY() + 1.8, entity.getZ(), 1, 0.2, 0.2, 0.2, 0.2);
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
