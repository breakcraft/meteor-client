import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;
import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("aj")
public class class20 {
    @ObfuscatedName("vb")
    @ObfuscatedSignature(
            descriptor = "Ldu;"
    )
    static ClientPreferences clientPreferences;
   @ObfuscatedName("af")
   final int field64;
   @ObfuscatedName("an")
   final Map field63;
   @ObfuscatedName("aw")
   final String field62;

   class20(String var1) {
      this.field64 = 400;
      this.field63 = null;
      this.field62 = "";
   }

   class20(HttpURLConnection var1) throws IOException {
      this.field64 = var1.getResponseCode();
      var1.getResponseMessage();
      this.field63 = var1.getHeaderFields();
      StringBuilder var2 = new StringBuilder();
      InputStream var3 = this.field64 >= 300 ? var1.getErrorStream() : var1.getInputStream();
      if (var3 != null) {
         InputStreamReader var4 = new InputStreamReader(var3);
         BufferedReader var5 = new BufferedReader(var4);

         String var6;
         while((var6 = var5.readLine()) != null) {
            var2.append(var6);
         }

         var3.close();
      }

      this.field62 = var2.toString();
   }

   @ObfuscatedName("af")
   @ObfuscatedSignature(
      descriptor = "(I)I",
      garbageValue = "-869061222"
   )
   public int method79() {
      return this.field64;
   }

   @ObfuscatedName("an")
   @ObfuscatedSignature(
      descriptor = "(I)Ljava/util/Map;",
      garbageValue = "-1956902698"
   )
   public Map method80() {
      return this.field63;
   }

   @ObfuscatedName("aw")
   @ObfuscatedSignature(
      descriptor = "(I)Ljava/lang/String;",
      garbageValue = "1415778501"
   )
   public String method81() {
      return this.field62;
   }

   @ObfuscatedName("ac")
   @ObfuscatedSignature(
      descriptor = "(III)I",
      garbageValue = "-964267539"
   )
   static int method83(int var0, int var1) {
      long var2 = (long)((var0 << 16) + var1);
      return Script.NetCache_currentResponse != null && Script.NetCache_currentResponse.key == var2 ? NetCache.NetCache_responseArchiveBuffer.offset * 99 / (NetCache.NetCache_responseArchiveBuffer.array.length - Script.NetCache_currentResponse.padding) + 1 : 0;
   }

   @ObfuscatedName("ag")
   @ObfuscatedSignature(
      descriptor = "(II)I",
      garbageValue = "854271946"
   )
   public static int method82(int var0) {
      class133 var2 = (class133)SequenceDefinition.SequenceDefinition_cachedModel.get((long)var0);
      class133 var1;
      if (var2 != null) {
         var1 = var2;
      } else {
         var2 = class211.method1180(SequenceDefinition.SequenceDefinition_animationsArchive, SequenceDefinition.SequenceDefinition_skeletonsArchive, var0, false);
         if (var2 != null) {
            SequenceDefinition.SequenceDefinition_cachedModel.put(var2, (long)var0);
         }

         var1 = var2;
      }

      if (var1 == null) {
         return 2;
      } else {
         return var1.method774() ? 0 : 1;
      }
   }
}
