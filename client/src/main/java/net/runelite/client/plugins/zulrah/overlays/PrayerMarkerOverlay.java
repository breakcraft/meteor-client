package net.runelite.client.plugins.zulrah.overlays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Prayer;
import net.runelite.api.VarClientInt;
import net.runelite.api.widgets.Widget;

import meteor.ui.overlay.Overlay;
import meteor.ui.overlay.OverlayLayer;
import meteor.ui.overlay.OverlayPosition;
import meteor.ui.overlay.OverlayPriority;
import net.runelite.client.plugins.zulrah.ZulrahConfig;
import net.runelite.client.plugins.zulrah.ZulrahPlugin;
import net.runelite.client.plugins.zulrah.util.OverlayUtils;

public class PrayerMarkerOverlay extends Overlay {
   private final Client client;
   private final ZulrahPlugin plugin;
   private final ZulrahConfig config;


   public PrayerMarkerOverlay(Client client, ZulrahPlugin plugin, ZulrahConfig config) {
      this.client = client;
      this.plugin = plugin;
      this.config = config;
      this.setPosition(OverlayPosition.DYNAMIC);
      this.setLayer(OverlayLayer.ABOVE_WIDGETS);
      this.setPriority(OverlayPriority.HIGH);
   }


   @Override
   public Dimension render(Graphics2D graphics) {
      if (config.prayerMarker() && plugin.getZulrahNpc() != null && !plugin.getZulrahNpc().isDead()) {
         plugin.getZulrahData().forEach((data) -> {
            data.getCurrentPhasePrayer().ifPresent((prayer) -> {
               if (client.getVarcIntValue(VarClientInt.INVENTORY_TAB.getIndex()) == 5) {
                  Widget widget = client.getWidget(541, prayerToChildId(prayer));
                  Color color = !client.isPrayerActive(prayer) ? Color.RED : Color.GREEN;
                  OverlayUtils.renderWidgetPolygon(graphics, widget, color, true, false);
               }

            });
         });
         return null;
      } else {
         return null;
      }
   }

   private int prayerToChildId(Prayer prayer) {
      switch(prayer) {
      case PROTECT_FROM_MELEE:
         return 19;
      case PROTECT_FROM_MISSILES:
         return 18;
      case PROTECT_FROM_MAGIC:
         return 17;
      default:
         return -1;
      }
   }
}
