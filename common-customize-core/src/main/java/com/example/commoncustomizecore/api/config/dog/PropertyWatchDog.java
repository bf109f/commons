package com.example.commoncustomizecore.api.config.dog;

public class PropertyWatchDog extends FileWatchDog
{
    PropertyWatchDog(String filename)
    {
        super(filename);
    }

    @Override
    protected void doOnChange()
    {

    }
}
