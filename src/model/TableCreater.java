package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class TableCreater {
	List<String> columnNames = new ArrayList<>();
	ObservableList<String[]> stringArrayData = FXCollections.observableArrayList();
	int nameWidth = 60, cellHeight = 26, cellWidth = 33;

	public TableCreater() {
		initStringArrayData();
	}

	private void initStringArrayData() {
		int length = Assigner.days;
		Assigner.start();
		// 初始化表头
		for (int i = 1; i <= length; i++) {
			if (i == 1)
				columnNames.add("Name");
			columnNames.add("" + i);
		}
		initArrayValue();
	}

	private void initArrayValue() {
		int length = Assigner.days;
		stringArrayData.clear();
		// 初始化值
		for (int i = 0; i < Assigner.getTeammateAmount(); i++) {
			String[] strArray = new String[length + 1]; // 新增一欄,第一欄為名字
			for (int j = 0, day = 1; j < length; j++, day++) {
				if (j == 0)
					strArray[0] = Assigner.team.get(i).name;
				// j+1 because [0] been take to set name
				strArray[j + 1] = Assigner.team.get(i).getTheDaySchedule(day);
			}
			stringArrayData.add(strArray);
		}
	}

	// 根据数组创建table
	public TableView<String[]> createTableByStringArray() {
		TableView<String[]> tableView = new TableView<>();
		for (final String name : columnNames) {
			TableColumn<String[], String> column = new TableColumn<>(name);
			column.setCellFactory(TextFieldTableCell.forTableColumn()); // cell become editable
			column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
					int index = columnNames.indexOf(name);
					String value = param.getValue()[index];
					return new SimpleStringProperty(value);
				}
			});

			column.setOnEditCommit(new EventHandler<CellEditEvent<String[], String>>() {
				@Override
				public void handle(CellEditEvent<String[], String> t) {
					String day = t.getTableColumn().getText();
					String oldVal = t.getOldValue();
					String newVal = t.getNewValue();
					if (!oldVal.equals("休") && !newVal.equals("休")) { // both is numeric, switch
						Assigner.team.switchDay(day, oldVal, newVal);
					} else if (!oldVal.equals("休") && newVal.equals("休")) { // numeric to day off
						int mate = t.getTablePosition().getRow();
						int _day = t.getTablePosition().getColumn();
						Assigner.team.get(mate).setSechule(_day, 1);
					}
					initArrayValue();
				}
			});
			column.setMaxWidth(cellWidth);
			tableView.getColumns().add(column);
		}
		tableView.getColumns().get(0).setMinWidth(nameWidth); // 'name' column need bigger
		tableView.setItems(stringArrayData);
		return getAvgTableView(tableView);
	}

	private TableView<String[]> getAvgTableView(TableView<String[]> table) {
		table.setMaxWidth(this.getWidth());
		table.setMinHeight(this.getHeight());
		return table;
	}

	public int getWidth() {
		return cellWidth * Assigner.days + nameWidth;
	}

	public int getHeight() {
		return cellHeight * Assigner.getTeammateAmount();
	}
}
