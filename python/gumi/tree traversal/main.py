class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None

    def __repr__(self):
        return repr(self.data)

    def insert(self, data):
        if self.data:
            if data < self.data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    self.left.insert(data)
            elif data > self.data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    self.right.insert(data)
        else:
            self.data = data

    def inorder(self, root):
        res = []
        if root:
            res = self.inorder(root.left)
            res.append(root.data)
            res = res + self.inorder(root.right)
        return res

    def preorder(self, root):
        res = []
        if root:
            res.append(root.data)
            res = res + self.preorder(root.left)
            res = res + self.preorder(root.right)
        return res

    def postorder(self, root):
        res = []
        if root:
            res = self.postorder(root.left)
            res = res + self.postorder(root.right)
            res.append(root.data)
        return res


def main():
    userinp = input('Enter the inorder traversal of the tree: ').split()
    root = Node(userinp[0])
    for i in range(1, len(userinp)):
        root.insert(userinp[i])
    print('Inorder traversal of the tree: ', root.inorder(root))
    choice = int(input('Enter 1 for preorder traversal, 2 for postorder traversal: '))
    match choice:
        case 1:
            input('Enter preorder traversal of the tree: ')
            print('Missing Travesal Identified!')
            print('Postorder traversal of the tree: ', root.postorder(root))
        case 2:
            input('Enter postorder traversal of the tree: ')
            print('Missing Travesal Identified!')
            print('Preorder traversal of the tree: ', root.preorder(root))


if __name__ == '__main__':
    main()
