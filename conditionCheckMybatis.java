public SelectStatementProvider search(Integer targetId, String fName, String lName) {
        var builder = select(id, firstName, lastName)    // (1)
        .from(person)
        .where();    // (2)

        if (targetId != null) {    // (3)
        builder
        .and(id, isEqualTo(targetId));
        } else {
        builder
        .and(firstName, isLike(fName).filter(Objects::nonNull).map(s -> "%" + s + "%"))    // (4) (5)
        .and(lastName, isLikeWhenPresent(lName).map(this::addWildcards));    // (6)
        }

        builder
        .orderBy(lastName, firstName)
        .fetchFirst(50).rowsOnly();    // (7)

        return builder.build().render(RenderingStrategies.MYBATIS3);    // (8)
        }

public String addWildcards(String s) {
        return "%" + s + "%";
        }