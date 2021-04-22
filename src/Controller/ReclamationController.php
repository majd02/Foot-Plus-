<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/", name="reclamation_index", methods={"GET"})
     */
    public function index(): Response
    {
        $reclamations = $this->getDoctrine()
            ->getRepository(Reclamation::class)
            ->findAll();

        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations,
        ]);
    }

    /**
     * @Route("/front", name="reclamationf_index", methods={"GET"})
     * @param PaginatorInterface $paginator

     * @param Request $request
     * @return Response
     */
    public function index2(PaginatorInterface $paginator, Request $request): Response
    {
        $reclamations = $this->getDoctrine()
            ->getRepository(Reclamation::class)
            ->findAll();
        $reclamations = $paginator->paginate(
            $reclamations,
            $request->query->getInt('page', 1),
            7);

        return $this->render('reclamationf/index.html.twig', [
            'reclamations' => $reclamations,
        ]);
    }

    /**
     * @Route("/front/new", name="reclamationf_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);
        $bantime = date('Y-m-d H:i:s', (time()));
        $reclamation->setDate(\DateTime::createFromFormat('Y-m-d H:i:s', $bantime));
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('reclamationf_index');
        }

        return $this->render('reclamationf/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/front/{id}", name="reclamationf_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamationf/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }
    /**
     * @Route("/TrierParDateDESC", name="TrierParDateDESC")
     */
    public function TrierParDate(Request $request): Response
    {
        $repository = $this->getDoctrine()->getRepository(Reclamation::class);
        $reclamation = $repository->findByDate();

        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamation,
        ]);
    }
    /**
     * @Route("/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show2(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }



    /**
     * @Route("/front/{id}/edit", name="reclamationf_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reclamation $reclamation): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamationf_index');
        }

        return $this->render('reclamationf/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/{id}/edit", name="reclamation_edit", methods={"GET","POST"})
     */
    public function edit2(Request $request, Reclamation $reclamation): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_index');
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/front/{id}", name="reclamationf_delete", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reclamationf_index');
    }
    /**
     * @Route("/{id}", name="reclamation_delete", methods={"POST"})
     */
    public function delete2(Request $request, Reclamation $reclamation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reclamation_index');
    }
    /**
     * @Route("/{id}/etataccAction", name="reclamation_etataccAction", methods={"GET","POST"})
     */
    public function etataccAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);

        $reclamation->setEtat("Accepted");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }
    /**
     * @Route("/{id}/etatrejAction", name="reclamation_etatrejAction", methods={"GET","POST"})
     */
    public function etatrejAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);
        $reclamation->setEtat("Rejected");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }
    /**
     * @Route("/{id}/etatdonAction", name="reclamation_etatdonAction", methods={"GET","POST"})
     */
    public function etatdonAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);
        $reclamation->setEtat("Done");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }
}
