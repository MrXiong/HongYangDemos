package com.zx.redenvelopes;

/**
 * created by user on 2017/11/28.
 */
public class Types{
    final class types {
        //所有事件
        public static final int types_all_mask = -1;
        //通知栏
        public static final int type_notification_state_changed = 64;
        //点击事件
        public static final int type_view_clicked = 1;
        //控件获取焦点
        public static final int type_view_focused = 8;
        //长按事件
        public static final int type_view_long_clicked = 2;
        //页面变动
        public static final int type_view_scrolled = 4096;
        //控件选中
        public static final int type_view_selected = 4;
        //输入框文本改变
        public static final int type_view_text_changed = 16;
        //输入框文本selection改变
        public static final int type_view_text_selection_changed = 8192;
        //窗口内容改变
        public static final int type_window_content_changed = 2048;
        //窗口状态改变
        public static final int type_window_state_changed = 32;
    }
}

