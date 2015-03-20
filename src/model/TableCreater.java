package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class TableCreater {
	ObservableList<Document> documentData = FXCollections.observableArrayList();
	List<String> columnNames = new ArrayList<>();
	ObservableList<String[]> stringArrayData = FXCollections.observableArrayList();

	public TableCreater() {
		initDocumentData();
		initStringArrayData();
	}

	private void initDocumentData() {
		documentData.clear();
		for (int i = 100; i < 130; i++) {
			documentData.add(new Document(i, "document" + i, "remark" + i));
		}
	}

	private void initStringArrayData() {
		int length = Assigner.days;
		Assigner.start();
		// 初始化表头
		for (int i = 1; i <= length; i++) {
			columnNames.add(""+i);
		}
		// 初始化值
		for (int i = 0; i < Assigner.getTeammateAmount(); i++) {
			String[] strArray = new String[length];
			for (int j = 0; j < length; j++) {
				strArray[j] = Assigner.team.get(i).getTheDaySchedule(j);
			}
			stringArrayData.add(strArray);
		}
	}

	// 根据数组创建table
	public TableView<String[]> createTableByStringArray() {
		TableView<String[]> tableView = new TableView<>();
		for (final String name : columnNames) {
			TableColumn<String[], String> column = new TableColumn<>(name);
			column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>,
					ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<String[], String> param) {
					int index = columnNames.indexOf(name);
					String value = param.getValue()[index];
					return new ReadOnlyStringWrapper(value);
				}
			});
			column.setMaxWidth(30);
			tableView.getColumns().add(column);
		}
		tableView.setItems(stringArrayData);
		return tableView;
	}
}
