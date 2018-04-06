package app.parser;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelParserImpl implements ModelParser
{
    private ModelMapper modelMapper;

    public ModelParserImpl()
    {
        this.modelMapper = new ModelMapper();
    }

    @Override
    public <S, D> D parse(S source, Class<D> destinationClass)
    {
        D convertedObject = null;
        convertedObject = this.modelMapper.map(source, destinationClass);
        return convertedObject;
    }
}
