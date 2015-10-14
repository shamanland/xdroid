package xdroid.options;

import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class EnumOption implements Option {
    private final Enum<?> mEnumConstant;

    public EnumOption(Enum<?> enumConstant) {
        mEnumConstant = notNull(enumConstant);
    }

    @Override
    public String getName() {
        return mEnumConstant.name();
    }
}
