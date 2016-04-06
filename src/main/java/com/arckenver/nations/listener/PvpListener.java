package com.arckenver.nations.listener;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.All;

import com.arckenver.nations.DataHandler;

public class PvpListener
{
	@Listener
	public void onEntityDamagedByPlayer(DamageEntityEvent event, @All(ignoreEmpty=false) EntityDamageSource[] sources)
	{
		Entity attacker = null;
		for (int i = 0; i < sources.length; i++)
		{
			if (sources[i].getSource().getType() == EntityTypes.PLAYER)
			{
				attacker = sources[i].getSource();
			}
		}
		if (attacker != null && event.getTargetEntity().getType() == EntityTypes.PLAYER)
		{
			if (!DataHandler.getFlag("pvp", attacker.getLocation()) || !DataHandler.getFlag("pvp", event.getTargetEntity().getLocation()))
			{
				event.setCancelled(true);
				return;
			}
		}
	}
}