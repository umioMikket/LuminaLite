package com.umiomikket.llite;

import com.umiomikket.llite.render.DefaultRenderContext;
import com.umiomikket.llite.render.IRenderContextFactory;

public class LuminaLite {
    private static IRenderContextFactory RENDER_CONTEXT_FACTORY;
    private static int FRAME_RATE_DELAY;
    private static int FRAME_RATE;

    static {
        RENDER_CONTEXT_FACTORY = DefaultRenderContext::new;
        LuminaLite.FRAME_RATE_DELAY = 1000 / 60;
        LuminaLite.FRAME_RATE = 60;
    }

    public static IRenderContextFactory getRenderContextFactory() {
        return RENDER_CONTEXT_FACTORY;
    }

    public static void setRenderContextFactory(IRenderContextFactory renderContextFactory) {
        RENDER_CONTEXT_FACTORY = renderContextFactory;
    }

    public static int getFrameRateDelay() {
        return FRAME_RATE_DELAY;
    }

    public static int getFramerate() {
        return FRAME_RATE;
    }

    public static void setFrameRate(int value) {
        LuminaLite.FRAME_RATE_DELAY = 1000 / value;
        LuminaLite.FRAME_RATE = value;
    }
}
