package org.cwitmer34.marketplace.handler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.cwitmer34.marketplace.TrialMarketplace;
import org.cwitmer34.marketplace.data.mongo.collect.PlayerCollect;
import org.cwitmer34.marketplace.guis.CollectGUI;
import org.cwitmer34.marketplace.util.GeneralUtil;

import java.util.List;
import java.util.UUID;

public class JoinHandler implements Listener {
	public JoinHandler() {
		TrialMarketplace.getPlugin().getServer().getPluginManager().registerEvents(this, TrialMarketplace.getPlugin());
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		TrialMarketplace.getCollectHandler().createCollect(player.getUniqueId().toString(), UUID.randomUUID().toString(), List.of());
		PlayerCollect playerCollect = TrialMarketplace.getCollectHandler().getCollect(player.getUniqueId().toString());
		if (!TrialMarketplace.getCollectGuis().containsKey(player.getUniqueId().toString())) {
			TrialMarketplace.getCollectGuis().put(player.getUniqueId().toString(), new CollectGUI());
		}

		if (!playerCollect.getSerializedItems().isEmpty()) return;

		player.sendMessage(GeneralUtil.prefix
						.append(Component.text("You have item(s) in your collect! Use ").color(NamedTextColor.LIGHT_PURPLE))
						.append(Component.text("/collect").color(NamedTextColor.YELLOW))
						.append(Component.text(" to claim them!").color(NamedTextColor.LIGHT_PURPLE)));

	}
}
