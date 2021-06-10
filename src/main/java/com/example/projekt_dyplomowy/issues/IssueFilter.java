package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.enums.State;
import com.example.projekt_dyplomowy.persons.Person;
import com.example.projekt_dyplomowy.projects.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor
@Getter
@Setter
public class IssueFilter {

    State state;
    Project project;
    Person assignee;
    String title;
    Boolean enabled = true;

    String globalSearch;

    private Specification<Issue> hasState() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("state"), state);
    }

    private Specification<Issue> hasProject() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("project"), project);
    }

    private Specification<Issue> hasAssignee() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("assignee"), assignee);
    }

    private Specification<Issue> hasTitle() {
        return (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("title")), "%" + title.toLowerCase() + "%");
    }

    private Specification<Issue> globalSearching() {

        Specification<Issue> hasTitle = (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("title")), "%" + globalSearch.toLowerCase() + "%");
        Specification<Issue> hasContent = (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("content")), "%" + globalSearch.toLowerCase() + "%");

        return hasTitle.or(hasContent);
    }

    private Specification<Issue> isEnabled() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("enabled"), enabled);
    }


    public Specification<Issue> buildQuery() {
        Specification<Issue> spec = Specification.where(null);

        if (project != null) {
            spec = spec.and(hasProject());
        }

        if (assignee != null) {
            spec = spec.and(hasAssignee());
        }

        if (state != null) {
            spec = spec.and(hasState());
        }

        if (title != null) {
            spec = spec.and(hasTitle());
        }

        if (globalSearch != null) {
            spec = spec.and(globalSearching());
        }
        if (enabled != null) {
            spec = spec.and(isEnabled());
        }

        return spec;

    }

    public String toQueryString(Integer page) {
        return "page=" + page +
                (state != null ? "&state=" + state : "") +
                (project != null ? "&project=" + project.getId() : "") +
                (assignee != null ? "&assignee=" + assignee.getId() : "") +
                (title != null ? "&title=" + title : "") +
                (globalSearch != null ? "&globalSearch=" + globalSearch : "") +
                (enabled != null ? "&enabled=" + enabled : "");
    }
}
