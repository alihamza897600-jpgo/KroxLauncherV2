package com.krox.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class KroxTitleScreen extends Screen {

    public KroxTitleScreen() {
        super(Text.literal("Krox Client"));
    }

    @Override
    protected void init() {

        int x = this.width / 2 - 100;
        int y = this.height / 2 - 40;

        addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Singleplayer"),
                button -> client.setScreen(
                    new SelectWorldScreen(this)
                )
            )
            .dimensions(x, y, 200, 20)
            .build()
        );

        addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Multiplayer"),
                button -> client.setScreen(
                    new MultiplayerScreen(this)
                )
            )
            .dimensions(x, y + 24, 200, 20)
            .build()
        );

        addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Krox Client"),
                button -> client.setScreen(
                    new ClickGuiScreen()
                )
            )
            .dimensions(x, y + 48, 200, 20)
            .build()
        );

        addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Options"),
                button -> client.setScreen(
                    new OptionsScreen(this, client.options)
                )
            )
            .dimensions(x, y + 72, 200, 20)
            .build()
        );

        addDrawableChild(
            ButtonWidget.builder(
                Text.literal("Quit"),
                button -> client.stop()
            )
            .dimensions(x, y + 96, 200, 20)
            .build()
        );
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {

        context.fill(
            0,
            0,
            width,
            height,
            0xFF090914
        );

        context.drawCenteredTextWithShadow(
            textRenderer,
            "KROX CLIENT",
            width / 2,
            height / 4,
            0xFFAAAAFF
        );

        context.drawCenteredTextWithShadow(
            textRenderer,
            "Minecraft 1.21.1",
            width / 2,
            height / 4 + 20,
            0xFFFFFFFF
        );

        context.drawCenteredTextWithShadow(
            textRenderer,
            "Press RIGHT SHIFT for Click GUI",
            width / 2,
            height - 30,
            0xFF888888
        );

        super.render(context, mouseX, mouseY, delta);
    }
}
