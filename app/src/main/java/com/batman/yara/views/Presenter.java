package com.batman.yara.views;

public class Presenter<T extends Presenter.View> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public void initialize() {
    }

    public void onDestroy() {
    }

    public interface View {
        default void showLoading() {
        }

        default void hideLoading() {
        }

        default void showMessage(String message) {
        }
    }
}
