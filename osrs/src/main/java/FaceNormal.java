import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ir")
@Implements("FaceNormal")
public class FaceNormal {
    @ObfuscatedName("af")
    int x;
    @ObfuscatedName("an")
    int y;
    @ObfuscatedName("aw")
    int z;

   @ObfuscatedName("au")
   @ObfuscatedSignature(
      descriptor = "(II)I",
      garbageValue = "-1628059522"
   )
   public static int method1294(int var0) {
      long var2 = ViewportMouse.ViewportMouse_entityTags[var0];
      int var1 = (int)(var2 >>> 0 & 127L);
      return var1;
   }

    @ObfuscatedName("aq")
    @ObfuscatedSignature(
            descriptor = "(I)Lcl;",
            garbageValue = "-285053165"
    )
    static World worldListStart() {
      World.World_listCount = 0;
      return Interpreter.getNextWorldListWorld();
   }

    @ObfuscatedName("aa")
    @ObfuscatedSignature(
            descriptor = "(IIIZII)J",
            garbageValue = "1232964320"
    )
    public static long calculateTag(int var0, int var1, int var2, boolean var3, int var4) {
      long var5 = (long)((var0 & 127) << 0 | (var1 & 127) << 7 | (var2 & 3) << 14) | ((long)var4 & 4294967295L) << 17;
      if (var3) {
         var5 |= 65536L;
      }

      return var5;
   }

    @ObfuscatedName("ay")
    @ObfuscatedSignature(
            descriptor = "(II)I",
            garbageValue = "858932471"
    )
    static int Messages_getLastChatID(int var0) {
      Message var1 = (Message)Messages.Messages_hashTable.get((long)var0);
      if (var1 == null) {
         return -1;
      } else {
         return var1.previousDual == Messages.Messages_queue.sentinel ? -1 : ((Message)var1.previousDual).count;
      }
   }
}
