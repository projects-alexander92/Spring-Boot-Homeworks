package app.parser;

public interface ModelParser
{
    public <S, D> D parse(S source, Class<D> destinationClass);
}
