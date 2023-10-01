package org.info.blog.demo.lombok;

public class BuilderTest {
    public BuilderTest(String name, String anotherName) {
        this.name = name;
        this.anotherName = anotherName;
    }

    private String name;
    private String anotherName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotehrName) {
        this.anotherName = anotehrName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String anotherName;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder anotherName(String name) {
            this.name = name;
            return this;
        }

        public BuilderTest build() {
            return new BuilderTest(name, anotherName);
        }


    }





}

