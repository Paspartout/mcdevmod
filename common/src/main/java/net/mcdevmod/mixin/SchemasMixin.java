package net.mcdevmod.mixin;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import net.mcdevmod.mod.LazyDataFixerBuilder;
import net.minecraft.datafixer.Schemas;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Mixin(Schemas.class)
public class SchemasMixin {
    private static long startTime;

    @Inject(method = "create", at = @At("HEAD"))
    private static void create$recordStart(CallbackInfoReturnable<DataFixer> cir) {
        startTime = System.nanoTime();
    }
    @Inject(method = "create", at = @At("RETURN"))
    private static void create$doEnd(CallbackInfoReturnable<DataFixer> cir) {
        new Thread(() -> {
            if (((ForkJoinPool) Util.getBootstrapExecutor()).awaitQuiescence(Integer.MAX_VALUE, TimeUnit.MILLISECONDS)) {
                System.out.println("Initialization complete in " + ((System.nanoTime() - startTime) / 1_000_000) + "ms");
            } else {
                System.out.println("Initialization still didn't complete in " + ((System.nanoTime() - startTime) / 1_000_000) + "ms");
            }
        }).start();
    }


    @Redirect(method = "create", at = @At(value = "NEW", target = "com/mojang/datafixers/DataFixerBuilder"))
    private static DataFixerBuilder create$replaceBuilder(int dataVersion) {
        return new LazyDataFixerBuilder(dataVersion);
    }
}
