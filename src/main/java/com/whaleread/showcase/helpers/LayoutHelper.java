package com.whaleread.showcase.helpers;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dolphin
 */
@Component
public class LayoutHelper implements Helper {
    private final Handlebars handlebars;

    public LayoutHelper(Handlebars handlebars) {
        this.handlebars = handlebars;
    }

    @Override
    public Object apply(Object context, Options options) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.putAll((Map<String, Object>)context);
        Map<String, Object> layout = new HashMap<>(options.hash);
        data.put("layout", layout);
        Template template = handlebars.compile("partial/layout");
        CharSequence body = options.fn(context);
        layout.put("body", body);
        return template.apply(data);
    }
}
