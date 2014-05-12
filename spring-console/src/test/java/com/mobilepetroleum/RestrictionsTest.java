package com.mobilepetroleum;

import org.junit.Test;

import static com.mobilepetroleum.Classes.findMethod;
import static org.fest.assertions.api.Assertions.assertThat;

public class RestrictionsTest {

    @Test
    public void shouldAllowPublicMethods() throws Exception {
        // given
        Restrictions restrictions = new Restrictions();
        restrictions.setWhitelist(new String[]{"public:com\\.mobilepetroleum:.*"});

        // when
        boolean allowed = restrictions.allowed(RestrictionsTest.class, findMethod(this, "shouldAllowPublicMethods").get());

        // then
        assertThat(allowed).isTrue();
    }

    @Test
    public void shouldAllowPrivateMethods() throws Exception {
        // given
        Restrictions restrictions = new Restrictions();
        restrictions.setWhitelist(new String[]{"private:com\\.mobilepetroleum:.*"});

        // when
        boolean allowed = restrictions.allowed(RestrictionsTest.class, findMethod(this, "shouldAllowPrivateMethods").get());

        // then
        assertThat(allowed).isFalse();
    }

    @Test
    public void shouldAllowAll() throws Exception {
        // given
        Restrictions restrictions = new Restrictions();
        restrictions.setWhitelist(new String[]{".*"});

        // when
        boolean allowed = restrictions.allowed(RestrictionsTest.class, findMethod(this, "shouldAllowAll").get());

        // then
        assertThat(allowed).isTrue();
    }

}