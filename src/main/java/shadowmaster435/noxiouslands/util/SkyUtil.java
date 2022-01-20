package shadowmaster435.noxiouslands.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadowmaster435.noxiouslands.dimension.NoxiousLandsDimension;

public class SkyUtil {
    public static World world = MinecraftClient.getInstance().world;

    private static final Identifier RED_SUN = new Identifier("noxiouslands:textures/environment/red_sun.png");
    private static final Identifier ATMOSPHERE = new Identifier("noxiouslands:textures/environment/toxicatmosphere.png");

    public static float ticks;
    public static int animticker;
    public static void changesun(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, Runnable runnable, CallbackInfo ci) {
        assert MinecraftClient.getInstance().player != null;
      //  System.out.println(MinecraftClient.getInstance().player.world.getRegistryKey().equals(NoxiousLandsDimension.WORLD_KEY));
        if (MinecraftClient.getInstance().player.world.getRegistryKey().equals(NoxiousLandsDimension.WORLD_KEY)) {
            float i;
            float k;
            BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();

            if (ticks % 10 == 0.0) {
               ++animticker;
                if (animticker >= 8) {
                    animticker = 0;
                }
            }

           // System.out.println(animticker);

            RenderSystem.enableTexture();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
            matrices.push();
            i = 1.0F - world.getRainGradient(tickDelta);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, i);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
            Matrix4f matrix4f2 = matrices.peek().getPositionMatrix();
            k = 30.0F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, RED_SUN);
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
            bufferBuilder.vertex(matrix4f2, -k, 100.0F, -k).texture(0.0F, 0.0F + (ticks * 0.0625F)).next();
            bufferBuilder.vertex(matrix4f2, k, 100.0F, -k).texture(1.0F, 0.0F + (ticks * 0.0625F)).next();
            bufferBuilder.vertex(matrix4f2, k, 100.0F, k).texture(1.0F, 0.0625F + (ticks * 0.0625F)).next();
            bufferBuilder.vertex(matrix4f2, -k, 100.0F, k).texture(0.0F, 0.0625F + (ticks * 0.0625F)).next();
            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            matrices.pop();
            RenderSystem.enableTexture();
            RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
            matrices.push();
            i = 1.0F - world.getRainGradient(tickDelta);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, i);
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
            k = 30.0F;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, ATMOSPHERE);
            bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
            bufferBuilder.vertex(matrix4f2, -100 * 8, 100.0F, -100  * 8).texture(0.0F + (ticks * 0.0625F) * 0.00125f, 0.0F + (ticks * 0.0625F) * 0.0025f).next();
            bufferBuilder.vertex(matrix4f2, 100  * 8, 100.0F, -100  * 8).texture(1.0F + (ticks * 0.0625F) * 0.00125f, 0.0F + (ticks * 0.0625F) * 0.0025f).next();
            bufferBuilder.vertex(matrix4f2, 100  * 8, 100.0F, 100  * 8).texture(1.0F + (ticks * 0.0625F) * 0.00125f, 1F + (ticks * 0.0625F) * 0.0025f).next();
            bufferBuilder.vertex(matrix4f2, -100  * 8, 100.0F, 100  * 8).texture(0.0F + (ticks * 0.0625F) * 0.00125f , 1F + (ticks * 0.0625F) * 0.0025f).next();
            bufferBuilder.color(1, 1, 1, (float) 0.4);

            bufferBuilder.end();
            BufferRenderer.draw(bufferBuilder);
            matrices.pop();
        }
    }

}
