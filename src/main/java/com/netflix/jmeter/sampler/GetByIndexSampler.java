package com.netflix.jmeter.sampler;

public class GetByIndexSampler extends AbstractSampler
{
    private static final long serialVersionUID = -2103499609822848595L;

    public ResponseData execute() throws OperationException
    {
        Operation ops = Connection.getInstance().newOperation(getColumnFamily(), false);
        setSerializers(ops);
        //return ops.getByIndex(getIndexName(), getIndexValue(), getColumnName());
        return ops.getByIndex(getKey(), getColumnName());
    }
}
