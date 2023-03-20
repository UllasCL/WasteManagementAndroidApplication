
<!--- Provide a general summary of your changes in the Title above -->

## What?
<!--- Describe what are the changes  -->


## Why?
<!--- Describe why the changes has been done -->


## Testing
<!--- Please describe in detail how you tested your changes. -->
<!--- Include details of your testing environment, tests ran to see how -->
<!--- your change affects other areas of the code, etc. -->
Test environment(s):

## Checklist

<% if (branchPrefix === 'feature/') { %>
- [ ] My code follows the code style of this project.
- [ ] I have updated the documentation accordingly.
- [ ] I have added tests to cover my changes.
- [ ] All new and existing tests passed.
- [ ] I have updated the CHANGELOG file.
- [ ] I have checked the branch for conflicts with the main branch.
- [ ] I have checked that my changes do not introduce any new linting errors or warnings.
<% } else if (branchPrefix === 'hotfix/') { %>
- [ ] My code follows the code style of this project.
- [ ] I have updated the documentation accordingly.
- [ ] I have added tests to cover my changes.
- [ ] All new and existing tests passed.
- [ ] I have checked the branch for conflicts with the main branch.
- [ ] I have checked that my changes do not introduce any new linting errors or warnings.
- [ ] My changes have been reviewed by a team member before merging.
<% } else { %>
- [ ] I have tested my changes locally before pushing them.
- [ ] I have checked the branch for conflicts with the main branch.
<% } %>
