package com.afa.devicer.dispatcher.sagas;

public class ExampleSaga {

}
/*
public class ExampleSaga implements SimpleSaga<ExampleSagaData> {

    @Override
    public SagaDefinition<ExampleSagaData> getSagaDefinition() {
        return null;
    }

    @Override
    public String getSagaType() {
        return SimpleSaga.super.getSagaType();
    }

    @Override
    public void onStarting(String sagaId, ExampleSagaData exampleSagaData) {
        SimpleSaga.super.onStarting(sagaId, exampleSagaData);
    }

    @Override
    public void onSagaCompletedSuccessfully(String sagaId, ExampleSagaData exampleSagaData) {
        SimpleSaga.super.onSagaCompletedSuccessfully(sagaId, exampleSagaData);
    }

    @Override
    public void onSagaRolledBack(String sagaId, ExampleSagaData exampleSagaData) {
        SimpleSaga.super.onSagaRolledBack(sagaId, exampleSagaData);
    }

    @Override
    public void onSagaFailed(String sagaId, ExampleSagaData exampleSagaData) {
        SimpleSaga.super.onSagaFailed(sagaId, exampleSagaData);
    }

    @Override
    public StepBuilder<ExampleSagaData> step() {
        return SimpleSaga.super.step();
    }
}

*/