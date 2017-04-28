package lombok;

import openlegacy.DummyInterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by matve on 24-Apr-17.
 * @since 3.6.0-SNAPSHOT
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface OLData {
    Class<?> value() default DummyInterface.class;

    boolean getters() default true;

    boolean setters() default true;
}
