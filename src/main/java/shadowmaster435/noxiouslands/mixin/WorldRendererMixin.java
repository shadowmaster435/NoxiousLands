package shadowmaster435.noxiouslands.mixin;


import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.noxiouslands.util.SkyUtil;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private int ticks;

    @Inject(at = @At("RETURN"), method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLjava/lang/Runnable;)V")
    private void renderRedSun(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, Runnable runnable, CallbackInfo ci) {

        SkyUtil.changesun(matrices, projectionMatrix, tickDelta, runnable, ci);
    }
    @Inject(at = @At("RETURN"), method = "tick")
    private void tick(CallbackInfo ci) {
        SkyUtil.ticks = this.ticks;
    }


}
