package com.netflix.jmeter.gui;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.jmeter.testelement.TestElement;

import com.netflix.jmeter.sampler.AbstractSampler;
import com.netflix.jmeter.sampler.GetByIndexSampler;

public class GetByIndex extends AbstractGUI
{
    private static final long serialVersionUID = 3197090412869386190L;
    public static String LABEL = "Cassandra Get by Index";
    private JTextField CNAME;
    private JComboBox CSERIALIZER;
    private JComboBox VSERIALIZER;

    @Override
    public void configure(TestElement element)
    {
        super.configure(element);
        CNAME.setText(element.getPropertyAsString(GetByIndexSampler.COLUMN_NAME));
        CSERIALIZER.setSelectedItem(element.getPropertyAsString(GetByIndexSampler.COLUMN_SERIALIZER_TYPE));
        VSERIALIZER.setSelectedItem(element.getPropertyAsString(GetByIndexSampler.VALUE_SERIALIZER_TYPE));
    }

    public TestElement createTestElement()
    {
        GetByIndexSampler sampler = new GetByIndexSampler();
        modifyTestElement(sampler);
        sampler.setComment("Row key is value to search index");
        return sampler;
    }

    public void modifyTestElement(TestElement sampler)
    {
        super.configureTestElement(sampler);

        if (sampler instanceof GetByIndexSampler)
        {
            GetByIndexSampler gSampler = (GetByIndexSampler) sampler;
            gSampler.setCSerializerType((String) CSERIALIZER.getSelectedItem());
            gSampler.setVSerializerType((String) VSERIALIZER.getSelectedItem());
            gSampler.setColumnName(CNAME.getText());
        }
    }

    public void initFields()
    {
        CNAME.setText("${__Random(1,1000)}");
        CSERIALIZER.setSelectedItem("StringSerializer");
        VSERIALIZER.setSelectedItem("StringSerializer");
    }

    @Override
    public void init(JPanel mainPanel, GridBagConstraints labelConstraints, GridBagConstraints editConstraints)
    {
    	//Ugly override of label? Nope
        addToPanel(mainPanel, labelConstraints, 0, 2, new JLabel("Index Value: ", JLabel.RIGHT));
        
        addToPanel(mainPanel, labelConstraints, 0, 3, new JLabel("Index (Column) Name: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 3, CNAME = new JTextField());
        addToPanel(mainPanel, labelConstraints, 0, 4, new JLabel("Column Serializer: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 4, CSERIALIZER = new JComboBox(AbstractSampler.getSerializerNames().toArray()));
        addToPanel(mainPanel, labelConstraints, 0, 5, new JLabel("Value Serializer: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, 5, VSERIALIZER = new JComboBox(AbstractSampler.getSerializerNames().toArray()));
    }

    @Override
    public String getLable()
    {
        return LABEL;
    }
}
