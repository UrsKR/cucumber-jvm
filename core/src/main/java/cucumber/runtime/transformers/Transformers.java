package cucumber.runtime.transformers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Class for transforming String arguments from Gherkin to a certain type using a Locale
 */
public class Transformers {
    private Map<Class<?>, Transformer<?>> transformables;

    public <T> T transform(Locale locale, Class<T> clazz, String... arguments) throws TransformationException {
        Transformer<T> transformer = getTransformer(clazz);
        if (transformer == null) {
            throw new TransformationException("Can't transform " + asList(arguments) + " to: " + clazz.getName() + ". No transformer found.");
        }
        return transformer.transform(locale, arguments);
    }

    @SuppressWarnings("unchecked")
    public <T> Transformer<T> getTransformer(Class<T> clazz) {
        return (Transformer<T>) getTransformers().get(clazz);
    }

    private Map<Class<?>, Transformer<?>> getTransformers() {
        if (this.transformables == null) {
            this.transformables = defaultTransformers();
        }
        return this.transformables;
    }

    protected Map<Class<?>, Transformer<?>> defaultTransformers() {
        Map<Class<?>, Transformer<?>> transformers = new HashMap<Class<?>, Transformer<?>>();
        transformers.put(String.class, new StringTransformer());
        transformers.put(Date.class, new DateTransformer());
        transformers.put(BigDecimal.class, new BigDecimalTransformer());
        transformers.put(BigIntegerTransformer.class, new BigIntegerTransformer());
        BooleanTransformer booleanTransformable = new BooleanTransformer();
        transformers.put(Boolean.TYPE, booleanTransformable);
        transformers.put(Boolean.class, booleanTransformable);
        ByteTransformer byteTransformable = new ByteTransformer();
        transformers.put(Byte.TYPE, byteTransformable);
        transformers.put(Byte.class, byteTransformable);
        CharacterTransformer characterTransformable = new CharacterTransformer();
        transformers.put(Character.TYPE, characterTransformable);
        transformers.put(Character.class, characterTransformable);
        DoubleTransformer doubleTransformable = new DoubleTransformer();
        transformers.put(Double.TYPE, doubleTransformable);
        transformers.put(Double.class, doubleTransformable);
        FloatTransformer floatTransformable = new FloatTransformer();
        transformers.put(Float.TYPE, floatTransformable);
        transformers.put(Float.class, floatTransformable);
        IntegerTransformer integerTransformable = new IntegerTransformer();
        transformers.put(Integer.TYPE, integerTransformable);
        transformers.put(Integer.class, integerTransformable);
        LongTransformer longTransformable = new LongTransformer();
        transformers.put(Long.TYPE, longTransformable);
        transformers.put(Long.class, longTransformable);
        ShortTransformer shortTransformable = new ShortTransformer();
        transformers.put(Short.TYPE, shortTransformable);
        transformers.put(Short.class, shortTransformable);
        return transformers;
    }
}
