package com.nanaki.hookcraft.clickgui;

import com.nanaki.hookcraft.clickgui.component.Button;
import com.nanaki.hookcraft.clickgui.component.Checkbox;
import com.nanaki.hookcraft.clickgui.component.Slider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    public String title;
    public int x, y, width, height;
    public boolean dragging, extend = true;
    public int dragX, dragY;
    public List<Button> modules = new ArrayList<>();

    private Slider activeSlider = null;

    public Frame(String title, int x, int y, int width, int height) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private List<RenderItem> getVisibleItems() {
        List<RenderItem> list = new ArrayList<>();
        if (!extend) return list;

        int currentY = y + height;
        for (Button mb : modules) {
            list.add(new RenderItem(0, currentY, mb));
            currentY += height;

            if (mb.extended) {
                for (Slider s : mb.sliders) {
                    list.add(new RenderItem(1, currentY, s));
                    currentY += height;
                }
                for (Checkbox cb : mb.checkboxes) {
                    list.add(new RenderItem(2, currentY, cb));
                    currentY += height;
                }
            }
        }
        return list;
    }

    public void render(DrawContext context, int mouseX, int mouseY) {
        int primaryColor = 0xAEFFFFFF;

        int surfaceColor = 0x6CFFFFFF;
        int onSurfaceColor = 0xFF00B0FF;
        int extendSurfaceColor = 0x3CFFFFFF;

        int checkBoxColor = 0x8CFFFFFF;
        int textLightColor = 0xFFFFFFFF;
        int textNightColor = 0xFF1A1A1A;

        if (dragging) {
            this.x = mouseX - dragX;
            this.y = mouseY - dragY;
        }

        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
        if (tr == null) return;

        context.fill(x, y, x + width, y + height, primaryColor);
        context.drawText(tr, title, x + 4, y + (height / 2 - 4), textNightColor, false);

        List<RenderItem> visibleItems = getVisibleItems();

        if (activeSlider != null) {
            double val = activeSlider.min + ((double) (mouseX - x) / width) * (activeSlider.max - activeSlider.min);
            activeSlider.setValue(val);
        }

        for (RenderItem item : visibleItems) {
            int itemY = item.itemY;

            switch (item.type) {
                case 0 -> {
                    Button mb = (Button) item.obj;
                    if (mb.isEnabled()) {
                        context.fill(x, itemY, x + width, itemY + height, onSurfaceColor);
                        context.drawText(tr, mb.name, x + 6, itemY + (height / 2 - 4), textLightColor, false);
                    } else {
                        context.fill(x, itemY, x + width, itemY + height, surfaceColor);
                        context.drawText(tr, mb.name, x + 6, itemY + (height / 2 - 4), textNightColor, false);
                    }
                }
                case 1 -> {
                    Slider s = (Slider) item.obj;
                    context.fill(x, itemY, x + width, itemY + height, extendSurfaceColor);

                    double renderWidth = ((s.getValue() - s.min) / (s.max - s.min)) * width;
                    renderWidth = Math.clamp(renderWidth, 0, width);

                    if (renderWidth > 0) {
                        context.fill(x, itemY, x + (int) renderWidth, itemY + height, onSurfaceColor);
                    }
                    context.drawText(tr, s.name + ": " + s.getDisplayValue(), x + 10, itemY + (height / 2 - 4), textLightColor, false);
                }
                case 2 -> {
                    Checkbox cb = (Checkbox) item.obj;
                    context.fill(x, itemY, x + width, itemY + height, extendSurfaceColor);

                    if (cb.getValue()) {
                        context.fill(x + 10, itemY + 4, x + 18, itemY + 12, onSurfaceColor);
                    } else {
                        context.fill(x + 10, itemY + 4, x + 18, itemY + 12, checkBoxColor);
                    }
                    context.drawText(tr, cb.name, x + 24, itemY + (height / 2 - 4), textLightColor, false);
                }
            }
        }
    }

    public void handleModuleClick(double mouseX, double mouseY, int button) {
        if (!extend) return;

        List<RenderItem> visibleItems = getVisibleItems();

        for (RenderItem item : visibleItems) {
            if (mouseX >= x && mouseX <= x + width && mouseY >= item.itemY && mouseY <= item.itemY + height) {
                switch (item.type) {
                    case 0 -> {
                        Button mb = (Button) item.obj;
                        if (button == 0) mb.toggle();
                        if (button == 1) mb.extended = !mb.extended;
                    }
                    case 1 -> {
                        if (button == 0) {
                            Slider s = (Slider) item.obj;
                            activeSlider = s;
                            double val = s.min + ((mouseX - x) / width) * (s.max - s.min);
                            s.setValue(val);
                        }
                    }
                    case 2 -> {
                        if (button == 0) {
                            Checkbox cb = (Checkbox) item.obj;
                            cb.toggle();
                        }
                    }
                }
                return;
            }
        }
    }

    public void mouseReleased(int button) {
        if (button == 0) {
            dragging = false;
            activeSlider = null;
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            } else if (button == 1) {
                extend = !extend;
            }
        }
    }

    private static class RenderItem {
        int type;
        int itemY;
        Object obj;

        RenderItem(int type, int itemY, Object obj) {
            this.type = type;
            this.itemY = itemY;
            this.obj = obj;
        }
    }

    private boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}