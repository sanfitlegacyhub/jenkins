import jenkins.model.Jenkins

// Define the parent view and the view to delete
def parentViewName = 'SPRO'  // Replace with the parent view name if nested, or use null for the top-level view
def viewNameToDelete = 'SPRO_11_1'  // Replace with the name of the view you want to delete

// Get Jenkins instance
def jenkins = Jenkins.instance

// Check if we're deleting a nested view or a top-level view
def parentView = parentViewName ? jenkins.getView(parentViewName) : jenkins

if (parentView == null) {
    println "Parent view '${parentViewName}' does not exist."
    return
}

// Get the view to delete
def viewToDelete = parentView.getView(viewNameToDelete)

if (viewToDelete == null) {
    println "View '${viewNameToDelete}' does not exist."
} else {
    // Delete the view
    parentView.deleteView(viewToDelete)
    println "View '${viewNameToDelete}' has been deleted."
}

// Save the changes
jenkins.save()
