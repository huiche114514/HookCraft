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
    public boolean dragging, extended = true;
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
        if (!extended) return list;

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
        if (dragging) {
            this.x = mouseX - dragX;
            this.y = mouseY - dragY;
        }

        TextRenderer tr = MinecraftClient.getInstance().textRenderer;
        if (tr == null) return;

        context.fill(x, y, x + width, y + height, 0xFF202020);
        context.drawTextWithShadow(tr, title, x + 4, y + (height / 2 - 4), 0xFFFFFFFF);

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
                    int color = mb.isEnabled() ? 0xFF00A9FF : 0xFF151515;
                    context.fill(x, itemY, x + width, itemY + height, color);
                    context.drawTextWithShadow(tr, mb.name, x + 6, itemY + (height / 2 - 4), 0xFFFFFFFF);
                }
                case 1 -> {
                    Slider s = (Slider) item.obj;
                    context.fill(x, itemY, x + width, itemY + height, 0xFF101010);
                    double renderWidth = ((s.getValue() - s.min) / (s.max - s.min)) * width;

                    renderWidth = Math.clamp(renderWidth, 0, width);

                    context.fill(x, itemY, x + (int) renderWidth, itemY + height, 0x9000A9FF);
                    context.drawTextWithShadow(tr, s.name + ": " + s.getDisplayValue(), x + 10, itemY + (height / 2 - 4), 0xFFCCCCCC);
                }
                case 2 -> {
                    Checkbox cb = (Checkbox) item.obj;
                    context.fill(x, itemY, x + width, itemY + height, 0xFF101010);
                    int boxColor = cb.getValue() ? 0xFF00A9FF : 0xFF303030;
                    context.fill(x + 10, itemY + 4, x + 18, itemY + 12, boxColor);
                    context.drawTextWithShadow(tr, cb.name, x + 24, itemY + (height / 2 - 4), 0xFFCCCCCC);
                }
            }
        }
    }

    public void handleModuleClick(double mouseX, double mouseY, int button) {
        if (!extended) return;

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
                extended = !extended;
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