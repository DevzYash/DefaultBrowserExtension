# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.yash.defbrowask.defaultbrowserask.Defaultbrowserask {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/yash/defbrowask/defaultbrowserask/repack'
-flattenpackagehierarchy
-dontpreverify
