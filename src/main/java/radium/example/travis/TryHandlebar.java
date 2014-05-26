package radium.example.travis;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class TryHandlebar {
    
    public static class Code {
        final public static int SUCCESS = 0;
        final public static int FAILURE = 1;
    }
    
    final public static String TEMPLATE = "Template.handlebars";
    
    public static void main(String[] arguments) {
        int code;
        try {
            Handlebars handlebars = new Handlebars();
            
            Template template = loadTemplate(handlebars);
            Context context = newContext(handlebars);
            
            System.out.println(template.apply(context));
            
            code = Code.SUCCESS;
        } catch (IOException e) {
            e.printStackTrace(System.err);
            
            code = Code.FAILURE;
        }
        
        //exit(code);
    }
    
    public static void exit(int code) {
        System.exit(code);
    }
    
    public static List<String> getWords() {
        return ImmutableList.<String>builder()
                .add("Hello")
                .add("World")
                .add("!")
            .build();
    }
    
    public static Template loadTemplate(Handlebars handlebars) throws IOException {
        String expression = Resources.asCharSource(Resources.getResource(TEMPLATE), Charsets.UTF_8).read();
        Template template = handlebars.compileInline(expression);
        return template;
    }
    
    public static Context newContext(Handlebars handlebars) {
        List<String> words = getWords();
        Map<String, Object> map = ImmutableMap.<String, Object>builder()
                .put("words", words)
            .build();
        
        Context context = Context.newContext(map);
        return context;
    }
    
}
