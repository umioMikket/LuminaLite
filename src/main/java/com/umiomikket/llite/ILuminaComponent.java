package com.umiomikket.llite;

import com.umiomikket.llite.render.RenderFunction;

public interface ILuminaComponent {
    ComponentHelper getHelper();
    RenderFunction getRenderFunction();
}
