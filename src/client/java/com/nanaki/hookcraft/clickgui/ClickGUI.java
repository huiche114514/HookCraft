package com.nanaki.hookcraft.clickgui;

import com.nanaki.hookcraft.clickgui.component.Button;
import com.nanaki.hookcraft.clickgui.setting.BooleanSetting;
import com.nanaki.hookcraft.clickgui.setting.DoubleSetting;
import com.nanaki.hookcraft.clickgui.setting.Setting;
import com.nanaki.hookcraft.modules.Module;
import com.nanaki.hookcraft.modules.ModuleManager;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {
    private final List<Frame> frames = new ArrayList<>();

    public ClickGUI() {
        super(Text.literal("HookCraft"));

        int startX = 50;
        for (Module.Category category : Module.Category.values()) {
            String categoryName = category.name().substring(0, 1).toUpperCase() + category.name().substring(1).toLowerCase();
            Frame frame = new Frame(categoryName, startX, 50, 100, 16);

            for (Module module : ModuleManager.getModulesByCategory(category)) {
                Button button = new Button(
                        module.getName(),
                        module::isEnabled,
                        module::setEnabled
                );

                for (Setting<?> setting : module.getSettings()) {
                    if (setting instanceof DoubleSetting ds) {
                        button.addSlider(ds);
                    } else if (setting instanceof BooleanSetting bs) {
                        button.addCheckbox(bs);
                    }
                }

                frame.modules.add(button);
            }

            frames.add(frame);
            startX += 110;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x00FFFFFF);
        super.render(context, mouseX, mouseY, delta);
        for (Frame frame : frames) {
            frame.render(context, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        for (Frame frame : frames) {
            frame.mouseClicked(click.x(), click.y(), click.button());
            frame.handleModuleClick(click.x(), click.y(), click.button());
        }
        return true;
    }

    @Override
    public boolean mouseReleased(Click click) {
        for (Frame frame : frames) {
            frame.mouseReleased(click.button());
        }
        return true;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}