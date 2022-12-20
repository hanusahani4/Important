SelectStatementProvider selectStatement = select(orderMaster.orderId, orderDate, orderLine.lineNumber, itemMaster.description, orderLine.quantity)
        .from(orderMaster, "om")
        .join(orderLine, "ol").on(orderMaster.orderId, equalTo(orderLine.orderId))
        .join(itemMaster, "im").on(orderLine.itemId, equalTo(itemMaster.itemId))
        .where(orderMaster.orderId, isEqualTo(2))
        .build()
        .render(RenderingStrategies.MYBATIS3);