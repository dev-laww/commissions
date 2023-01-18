class BinaryTree:
    def __init__(self, key=None):
        self.key = key
        self.left = None
        self.right = None

    def __repr__(self):
        return repr(self.key)

    def add_left(self, node):
        self.left = node
        if node is not None:
            node.parent = self

    def add_right(self, node):
        self.right = node
        if node is not None:
            node.parent = self


def inorder_traversal(node):
    if node is None:
        return
    inorder_traversal(node.left)
    print(f'{node.key} -> ', end='')
    inorder_traversal(node.right)


def preorder_traversal(node):
    if node is None:
        return
    print(f'{node.key} -> ', end='')
    preorder_traversal(node.left)
    preorder_traversal(node.right)


def postorder_traversal(node):
    if node is None:
        return
    postorder_traversal(node.left)
    postorder_traversal(node.right)
    print(f'{node.key} -> ', end='')


def create_stack(root, arr):
    stack = [root]
    for i in range(1, len(arr)):
        node = BinaryTree(arr[i])
        if node.key < stack[-1].key:
            stack[-1].add_left(node)
        else:
            parent = None
            while len(stack) > 0 and node.key > stack[-1].key:
                parent = stack.pop()
            parent.add_right(node)
        stack.append(node)


def main():
    # get inorder traversal of the tree.py from user
    user_input = input('Enter the inorder traversal: ').split()
    inorder = [int(i) for i in user_input]
    root = BinaryTree(inorder[0])
    # create a stack to store the nodes
    create_stack(root, inorder)
    inorder_traversal(root)

    print('''\n
[1] Postorder Traversal
[2] Preorder Traversal
        ''')
    choice = int(input('Enter your choice: '))
    match choice:
        case 1:
            postoder = input('Enter the postorder traversal: ').split()
            postorder = [int(i) for i in postoder]
            create_stack(root, postorder)
            print('Postorder Traversal: ', end='')
            postorder_traversal(root)
            print('\n\nMissing Traversal Identified!')
            print('Preorder Traversal: ', end='')
            preorder_traversal(root)
            # A B C D E F G
            # 1 2 3 4 5 6 7
        case 2:
            preorder = input('Enter the postorder traversal: ').split()
            preorder = [int(i) for i in preorder]
            create_stack(root, preorder)
            print('Preorder Traversal: ', end='')
            preorder_traversal(root)
            print('\n\nMissing Traversal Identified!')
            print('Postorder Traversal: ', end='')
            postorder_traversal(root)
        case _:
            print('Invalid Choice')


if __name__ == '__main__':
    main()
