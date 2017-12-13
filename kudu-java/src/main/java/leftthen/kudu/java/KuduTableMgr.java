package leftthen.kudu.java;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.shaded.com.google.common.collect.ImmutableList;

import java.util.ArrayList;

public class KuduTableMgr {

    public static Schema getBasicSchema() {
        ArrayList<ColumnSchema> columns = new ArrayList<ColumnSchema>(5);
        columns.add(new ColumnSchema.ColumnSchemaBuilder("key", Type.INT32).key(true).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column1_i", Type.INT32).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column2_i", Type.INT32).build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column3_s", Type.STRING)
                .nullable(true)
                .desiredBlockSize(4096)
                .encoding(ColumnSchema.Encoding.DICT_ENCODING)
                .compressionAlgorithm(ColumnSchema.CompressionAlgorithm.LZ4)
                .build());
        columns.add(new ColumnSchema.ColumnSchemaBuilder("column4_b", Type.BOOL).build());
        return new Schema(columns);
    }

    public static CreateTableOptions getBasicCreateTableOptions() {
        return new CreateTableOptions().setRangePartitionColumns(ImmutableList.of("key"));
    }
}
