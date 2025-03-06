<?php
include "connection.php";

$today = date_create(date("Y-m-d"));
$oldDate = date_sub($today, date_interval_create_from_date_string("7 days"));

$invoiceDates_rs = Database::search("SELECT DISTINCT(DATE(`date`)) AS `date` FROM `invoice` 
                    INNER JOIN `invoice_item` ON `invoice`.`id`=`invoice_item`.`invoice_id`
                    WHERE (DATE(`date`) BETWEEN '" . date_format($oldDate, "Y-m-d") . "' AND'" . date("Y-m-d") . "') ORDER BY `date` ASC ;");

$invoiceDates_num = $invoiceDates_rs->num_rows;

$sales_array;
$date_array;

for ($x = 0; $x < $invoiceDates_num; $x++) {
    $invoiceDates = $invoiceDates_rs->fetch_assoc();
    $date_array[$x]=$invoiceDates["date"];

    $sale_rs = Database::search("SELECT SUM(`total`) AS `sum` FROM `invoice` 
    WHERE DATE(`date`) LIKE '" . $invoiceDates["date"] . "' ");
    $sale_data = $sale_rs->fetch_assoc();
    
    $sales_array[$x]=$sale_data["sum"];
    
}

$array;
$array["date"]=$date_array;
$array["sale"]=$sales_array;

echo(json_encode($array));

