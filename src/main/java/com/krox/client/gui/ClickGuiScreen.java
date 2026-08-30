package com.krox.client.gui;

import com.krox.client.KroxClient;
import com.krox.client.module.Category;
import com.krox.client.module.Module;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {

    private Category selectedCategory = Category.COMBAT;

    public ClickGuiScreen() {
        super(Text.literal("Krox Client"));
    }

    @Override
    protected void init() {
        buildGui();
    }

    private void buildGui() {

        this.clearChildren();

        int sidebarX = 20;
        int sidebarY = 50;

        for (Category category : Category.values()) {

            Category current = category;

            this.addDrawableChild(
                ButtonWidget.builder(
                    Text.literal(category.name()),
                    button -> {
                        selectedCategory = current;
                        buildGui();
                    }
                )
                .dimensions(sidebarX, sidebarY, 110, 20)
                .build()
            );

            sidebarY += 24;
        }

        int moduleX = 160;
        int moduleY = 50;

        for (Module module :
                KroxClient.MODULE_MANAGER.getModules(selectedCategory)) {

            this.addDrawableChild(
                ButtonWidget.builder(
                    Text.literal(
                        module.getName()
                        + " : "
                        + (module.isEnabled() ? "ON" : "OFF")
                    ),
                    button -> {
                        module.toggle();
                        buildGui();
                    }
                )
                .dimensions(moduleX, moduleY, 180, 20)
                .build()
            );

            moduleY += 24;
        }
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {

        context.fill(
            0, 0,
            this.width,
            this.height,
            0xCC08080F
        );

        context.fill(
            15, 15,
            145,
            this.height - 15,
            0xFF151522
        );

        context.fill(
            145, 15,
            this.width - 15,
            this.height - 15,
            0xFF101018
        );

        context.drawTextWithShadow(
            this.textRenderer,
            "KROX CLIENT",
            30,
            25,
            0xFFFFFFFF
        );

        context.drawTextWithShadow(
            this.textRenderer,
            selectedCategory.name(),
            160,
            25,
            0xFFAAAAFF
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
