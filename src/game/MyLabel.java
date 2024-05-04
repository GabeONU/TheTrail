package game;

import javax.swing.JLabel;

class MyLabel extends JLabel {
    private boolean fireEvents = true;

    @Override
    public void setText(String text) {
        fireEvents = false;
        super.setText(text);
        fireEvents = true;
    }

    @Override
    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (fireEvents) {
            super.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

    @Override
    public void revalidate() {
        // Do nothing
    }

    @Override
    public void repaint() {
        // Do nothing
    }
}
