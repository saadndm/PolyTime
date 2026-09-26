package org.polyfrost.polytime.mixin;

//? if < 26.1 {
/*import net.minecraft.client.multiplayer.ClientLevel;
import org.polyfrost.polytime.client.PolyTimeClient;
import org.polyfrost.polytime.client.PolyTimeConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
//? if > 1.8.9 {
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//?} else {
/^import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.Level;
^///?}

//? if > 1.8.9 {
@Mixin(ClientLevel.ClientLevelData.class)
public class Mixin_ClientLevelData {
    @Inject(method = "getDayTime", at = @At("RETURN"), cancellable = true)
    private void polytime$overrideDayTime(CallbackInfoReturnable<Long> cir) {
        if (PolyTimeConfig.isEnabled()) {
            cir.setReturnValue(PolyTimeClient.adjustTicks(cir.getReturnValue()));
        }
    }
}
//?} else {
/^@Mixin(Level.class)
public class Mixin_ClientLevelData {
    @ModifyExpressionValue(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldData;getTimeOfDay()J"))
    private long polytime$overrideDayTime(long original) {
        if ((Object) this instanceof ClientLevel && PolyTimeConfig.isEnabled()) {
            return PolyTimeClient.adjustTicks(original);
        }
        return original;
    }
}
^///?}
*///?} else {
import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SharedConstants.class)
public class Mixin_ClientLevelData {}
//?}
